package com.example.filter;

//@Component
//@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
//class MyFilter extends GenericFilterBean {
//    private final Tracer tracer;
//
//    MyFilter(Tracer tracer) {
//        this.tracer = tracer;
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        Span currentSpan = this.tracer.currentSpan();
//        if (currentSpan == null) {
//            chain.doFilter(request, response);
//            return;
//        }
//        ((HttpServletResponse) response).addHeader("ZIPKIN-TRACE-ID", currentSpan.context().traceIdString());
//        currentSpan.tag("custom", "tag");
//        chain.doFilter(request, response);
//    }
//}