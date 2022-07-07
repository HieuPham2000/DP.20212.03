package utils;

import entity.media.Media;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaTypesHolder {

    public static final List<String> MEDIA_TYPES = getAllMediaTypes();

    private static List<String> getAllMediaTypes() {
        Reflections reflections = new Reflections("entity.media");
        Set<String> mediaTypeNames = new HashSet<>();
        Set<Class<? extends Media>> allClasses = reflections.getSubTypesOf(Media.class);
        for (Class<? extends Media> c : allClasses) {
            mediaTypeNames.add(c.getSimpleName());
        }
        return new ArrayList<>(mediaTypeNames);
    }

}
