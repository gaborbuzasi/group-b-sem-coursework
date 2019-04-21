package com.napier.sem;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class containing code related to generating the report menu ui
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /**
     * Index action identifies all endpoints created in the com.napier.sem package
     * and creates the report selection UI using reflection
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String Index(Model model)
    {
        List<String> controllerNames = new ArrayList<>();

        List<ReportEndpoint> endpoints = new ArrayList<>();

        //Class scanner used to identify all controllers with endpoints that are used to retrieve data
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Controller.class));

        //Find all classes in the com.napier.sem package
        for (BeanDefinition bf : scanner.findCandidateComponents("com.napier.sem"))
        {
            String className = bf.getBeanClassName();

            // We don't want to generate endpoint data for home controller as this doesn't contain data
            if (!className.contains("HomeController"))
            {
                controllerNames.add(className);
            }
        }

        for (String controllerName : controllerNames)
        {
            try
            {
                Object controller = HomeController.class.getClassLoader().loadClass(controllerName);

                //Each controller endpoint contains its URL in the RequestMapping annotation
                String baseControllerUrl = ((RequestMapping)((Class) controller).getAnnotation(RequestMapping.class)).value()[0];

                // Iterate through each method in controller
                for (Method m : HomeController.class.getClassLoader()
                                                .loadClass(controllerName)
                                                .getDeclaredMethods())
                {
                    ReportEndpoint re = new ReportEndpoint();

                    // The full URL of the endpoint will be the URL of the controller plus the endpoint's
                    String endpointUrl = baseControllerUrl + m.getAnnotation(RequestMapping.class).value()[0];
                    String endpointName = m.getAnnotation(NameAnnotation.class).value();

                    Parameter[] params = m.getParameters();
                    for (Parameter p : params)
                    {
                        // If we have endpoints requiring parameters we need to store the name of the parameters
                        if (p.getAnnotation(RequestParam.class) != null)
                        {
                            ParameterModel pm = new ParameterModel();
                            pm.Name = p.getName();
                            pm.Id = endpointName.replace(" ", "") + pm.Name;

                            re.Parameters.add(pm);
                        }
                    }

                    re.Name = endpointName;
                    re.Url = endpointUrl;

                    // This is used to generate the required javascript for the UI
                    // to pass user input as arguments to endpoints
                    StringBuilder sb = new StringBuilder();
                    sb.append("document.getElementById('btn" + re.Name.replace(" ", "") + "').addEventListener('click', function () { ");

                    for (ParameterModel param : re.Parameters)
                    {
                        sb.append("var " + param.Id + "Value = document.getElementById('" + param.Id + "').value; ");
                    }

                    sb.append("window.location.href = '" + re.Url);

                    if (!re.Parameters.isEmpty())
                    {
                        sb.append("?");
                    }
                    else
                    {
                        sb.append("'");
                    }

                    for (ParameterModel param : re.Parameters)
                    {
                        sb.append(param.Name + "=" + "' + " + param.Id + "Value");

                        if (re.Parameters.indexOf(param) < re.Parameters.size() - 1)
                        {
                            sb.append(" + '&");
                        }
                    }

                    sb.append("; });");

                    re.Javascript = sb.toString();

                    endpoints.add(re);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        StringBuilder allJavascriptCode = new StringBuilder();

        for (ReportEndpoint re : endpoints)
        {
            if (re.Javascript != null)
                allJavascriptCode.append(re.Javascript);
        }

        model.addAttribute("javascript", allJavascriptCode.toString());
        model.addAttribute("endpoints", endpoints);

        return "index";
    }
}
