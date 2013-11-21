@Override
public void start() throws Exception {
  LOGGER.info("Start the report service - start.");
 
  MBeanServer server = MBeanServerLocator.locate();
  ServerConfigImplMBean<?> serverConfig = (ServerConfigImplMBean<?>) MBeanProxyExt.create(ServerConfigImplMBean.class, ServerConfigImplMBean.OBJECT_NAME, server);
 
  String birtRuntimeDir = serverConfig.getServerHomeLocation().getPath() + "/birt/";
  LOGGER.info("Birt runtime directory: " + birtRuntimeDir);
 
  reportsHomeDirectory = serverConfig.getServerHomeLocation().getPath() + "/reports/";
  LOGGER.info("Birt reports directory: " + reportsHomeDirectory);
 
  if (engine == null) {
 
    LOGGER.info("Create BIRT engine - start.");
 
    System.setProperty("RUN_UNDER_ECLIPSE", "false");
 
    EngineConfig engineConfig = new EngineConfig();
    engineConfig.setEngineHome(birtRuntimeDir);
    engineConfig.setResourcePath(reportsHomeDirectory);
 
    engineConfig.getAppContext().put(EngineConstants.APPCONTEXT_CLASSLOADER_KEY, BirtReportService.class.getClassLoader());
 
    try {
      Platform.startup(engineConfig);
    } catch (BirtException ex) {
      LOGGER.error("Error by starting the BIRT platform.", ex);
    }
 
    IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
    engine = factory.createReportEngine(engineConfig);
    engine.changeLogLevel(Level.FINE);
 
    LOGGER.info("Create BIRT engine - end.");
  }
 
  LOGGER.info("Start the report service - start.");
}