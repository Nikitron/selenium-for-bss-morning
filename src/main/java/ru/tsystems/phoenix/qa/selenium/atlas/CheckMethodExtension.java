package ru.tsystems.phoenix.qa.selenium.atlas;

import io.qameta.atlas.core.api.MethodExtension;
import io.qameta.atlas.core.internal.Configuration;
import io.qameta.atlas.core.util.MethodInfo;
import lombok.val;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CheckMethodExtension implements MethodExtension {
    public static ThreadLocal<List<String>> errorsList = ThreadLocal.withInitial(ArrayList::new);

    @Override
    public boolean test(Method method) {
        return method.getName().equals("check");
    }

    @Override
    public Object invoke(Object proxy, MethodInfo methodInfo, Configuration config) {
        val reason = methodInfo.getParameter(String.class)
                .orElse("Precondition not meat for element " + proxy.toString());
        val matcher = methodInfo.getParameter(Matcher.class).orElseThrow();

        if(!matcher.matches(proxy)) {
            final StringDescription description = new StringDescription();
            description.appendText(reason)
                    .appendText("\nExpected: ")
                    .appendDescriptionOf(matcher)
                    .appendText("\n     but: ");
            matcher.describeMismatch(proxy, description);
            String errorMessage = description.toString();
            System.out.println(errorMessage);
            errorsList.get().add(errorMessage);
        }
        return proxy;
    }
}
