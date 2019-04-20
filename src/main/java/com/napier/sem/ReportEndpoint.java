package com.napier.sem;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportEndpoint model is used to pass application endpoint information to the report menu UI
 */
public class ReportEndpoint {
    public String Name;
    public String Url;
    public List<ParameterModel> Parameters;
    public String Javascript;

    public ReportEndpoint()
    {
        Parameters = new ArrayList<>();
    }
}
