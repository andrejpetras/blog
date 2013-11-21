@Override
public void stop() throws Exception {
  LOGGER.info("Stop the BIRT service - start.");
 
  if (engine != null) {
    LOGGER.info("Destroy BIRT engine.");
    engine.destroy();
  }
 
  LOGGER.info("Stop the BIRT service - end.");
}