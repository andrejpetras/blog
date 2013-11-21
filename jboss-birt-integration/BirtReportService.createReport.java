@Override
public byte[] createReport(String reportName, Map<String, Object> parameters, String outputFormat, Locale locale) throws Exception {
 
  LOGGER.info("Create the report - start.");
 
  byte[] result = null;
 
  String reportPath = reportsHomeDirectory + reportName + ".rptdesign";
  if (reportPath == null || reportPath.isEmpty()) {
    throw new Exception("The report path is null or empty.");
  } else {
    File reportFile = new File(reportPath);
    if (!reportFile.exists()) {
      throw new Exception("Report file not found! Report: " + reportPath);
    }
  }
 
  try {
    // Open the report design     
    LOGGER.info("Report: " + reportPath);
 
    IReportRunnable design = engine.openReportDesign(reportPath);
 
    LOGGER.info("Create report options.");
    // Options
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    HTMLRenderOption options = new HTMLRenderOption();
    options.setImageDirectory("/image/");
    options.setOutputStream(stream);
    options.setOutputFormat(outputFormat);
 
    LOGGER.info("Create report render task.");
    // Create task to run and render the report
    IRunAndRenderTask task = engine.createRunAndRenderTask(design);
    task.setLocale(locale);
    task.setRenderOption(options);
    task.setParameterValues(parameters);
 
    LOGGER.info("Run report task.");
    task.run();
    task.close();
 
    LOGGER.info("The report output size: " + stream.size());
    result = stream.toByteArray();
 
  } catch (Exception ex) {
    throw new Exception("Generated report failed", ex);
  }
 
  LOGGER.info("Create the report - end.");
  return result;
}