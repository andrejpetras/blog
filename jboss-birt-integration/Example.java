@EJB
private ReportService reportService;
 
public byte[] callReport() throws Exception {
 
  Map<String, Object> parameters = new HashMap<String, Object>();
  parameters.put("parameter1", "Value1");
  parameters.put("parameter2", 14343);
 
  byte[] result = null;
  try {
    result = reportService.createReport("report1", parameters, "pdf", Locale.ENGLISH);
  } catch (Exception ex) {
    throw ex;
  }
  return result;    
}