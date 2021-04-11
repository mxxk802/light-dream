word转pdf

Report generation with Converter
Here a sample to generate report from ODT with Velocity and convert it to PDF format.

// 1) Load ODT file and set Velocity template engine and cache it to the registry
InputStream in= new FileInputStream(new File("ODTHelloWordWithVelocity.odt"));
IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);

// 2) Create Java model context
IContext context = report.createContext();
context.put("name", "world");

// 3) Set PDF as format converter
Options options = Options.getTo(ConverterTypeTo.PDF);

// 3) Generate report by merging Java model with the ODT and convert it to PDF
OutputStream out = new FileOutputStream(new File("ODTHelloWordWithVelocity_Out.odt"));
report.convert(context, options, out);