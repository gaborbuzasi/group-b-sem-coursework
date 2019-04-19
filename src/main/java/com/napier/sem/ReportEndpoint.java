package com.napier.sem;

import java.util.ArrayList;
import java.util.List;

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
