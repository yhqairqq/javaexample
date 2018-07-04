package com.ako.example.jdk.exec;

import org.apache.commons.exec.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Created by yanghuanqing@wdai.com on 25/07/2017.
 */
public class TutorialTest {

    /**
     * the directory to pick up the test scripts
     */
    private final File testDir = new File("src/test/scripts");

    /**
     * simulates a PDF print job
     */
    private final File acroRd32Script = TestUtil.resolveScriptForOS(testDir + "/acrord32");

    @Test
    public void testTutorialExample() throws Exception {

        final long printJobTimeout = 15000;
        final boolean printInBackground = false;
        final File pdfFile = new File("/Documents and Settings/foo.pdf");

        PrintResultHandler printResult;

        try {
            // printing takes around 10 seconds
            System.out.println("[main] Preparing print job ...");
            printResult = print(pdfFile, printJobTimeout, printInBackground);
            System.out.println("[main] Successfully sent the print job ...");
        } catch (final Exception e) {
            e.printStackTrace();
            fail("[main] Printing of the following document failed : " + pdfFile.getAbsolutePath());
            throw e;
        }

        // come back to check the print result
        System.out.println("[main] Test is exiting but waiting for the print job to finish...");
        printResult.waitFor();
        System.out.println("[main] The print job has finished ...");
    }

    /**
     * Simulate printing a PDF document.
     *
     * @param file              the file to print
     * @param printJobTimeout   the printJobTimeout (ms) before the watchdog terminates the print process
     * @param printInBackground printing done in the background or blocking
     * @return a print result handler (implementing a future)
     * @throws IOException the test failed
     */
    public PrintResultHandler print(final File file, final long printJobTimeout, final boolean printInBackground)
            throws IOException {

        int exitValue;
        ExecuteWatchdog watchdog = null;
        PrintResultHandler resultHandler;

        // build up the command line to using a 'java.io.File'
        final Map<String, File> map = new HashMap<String, File>();
        map.put("file", file);
        final CommandLine commandLine = new CommandLine(acroRd32Script);
        commandLine.addArgument("/p");
        commandLine.addArgument("/h");
        commandLine.addArgument("${file}");
        commandLine.setSubstitutionMap(map);

        // create the executor and consider the exitValue '1' as success
        final Executor executor = new DefaultExecutor();
        executor.setExitValue(1);

        // create a watchdog if requested
        if (printJobTimeout > 0) {
            watchdog = new ExecuteWatchdog(printJobTimeout);
            executor.setWatchdog(watchdog);
        }

        // pass a "ExecuteResultHandler" when doing background printing
        if (printInBackground) {
            System.out.println("[print] Executing non-blocking print job  ...");
            resultHandler = new PrintResultHandler(watchdog);
            executor.execute(commandLine, resultHandler);
        } else {
            System.out.println("[print] Executing blocking print job  ...");
            exitValue = executor.execute(commandLine);
            resultHandler = new PrintResultHandler(exitValue);
        }

        return resultHandler;
    }

    private class PrintResultHandler extends DefaultExecuteResultHandler {

        private ExecuteWatchdog watchdog;

        public PrintResultHandler(final ExecuteWatchdog watchdog) {
            this.watchdog = watchdog;
        }

        public PrintResultHandler(final int exitValue) {
            super.onProcessComplete(exitValue);
        }

        @Override
        public void onProcessComplete(final int exitValue) {
            super.onProcessComplete(exitValue);
            System.out.println("[resultHandler] The document was successfully printed ...");
        }

        @Override
        public void onProcessFailed(final ExecuteException e) {
            super.onProcessFailed(e);
            if (watchdog != null && watchdog.killedProcess()) {
                System.err.println("[resultHandler] The print process timed out");
            } else {
                System.err.println("[resultHandler] The print process failed to do : " + e.getMessage());
            }
        }
    }
}
