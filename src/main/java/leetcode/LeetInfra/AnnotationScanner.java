package leetcode.LeetInfra;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class AnnotationScanner {

    @SuppressWarnings("unchecked")
    public static <T> List<Class<? extends T>> findAnnotated(
            String packageName,
            Class<? extends java.lang.annotation.Annotation> annotationClass,
            Class<T> parentType
    ) {
        List<Class<? extends T>> result = new ArrayList<>();

        String path = packageName.replace('.', '/');
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL resource = loader.getResource(path);
        if (resource == null) return result;

        File directory = new File(resource.getFile());
        if (!directory.exists()) return result;

        for (String file : Objects.requireNonNull(directory.list())) {
            if (!file.endsWith(".class")) continue;

            String className = packageName + '.' + file.substring(0, file.length() - 6);
            try {
                Class<?> clazz = Class.forName(className);

                // Must be annotated and a subclass of parentType
                if (clazz.isAnnotationPresent(annotationClass)
                        && parentType.isAssignableFrom(clazz)) {
                    result.add((Class<? extends T>) clazz);
                }
            } catch (Throwable ignored) { }
        }
        return result;
    }
}
