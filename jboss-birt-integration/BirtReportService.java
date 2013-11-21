@Service(name = "BirtReportService")
@Local(ReportService.class)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@Management(BirtReportServiceMBean.class)
@PermitAll
public class BirtReportService implements BirtReportServiceMBean, ReportService {