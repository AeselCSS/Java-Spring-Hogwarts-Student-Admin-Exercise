package kea.exercise.hogwartsstudentadmin.edu.hogwarts.utility;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;

public class MiscUtility {
    // This method should copy all non-null properties from the source object to the target object, but it doesn't work properly.
    public static void copyNonNullProperties(Object src, Object target) {
        // Create a BeanWrapper for the source object and the target object
        // NOTE: A BeanWrapper is a wrapper for a bean which allows easy access to the properties of the bean
        BeanWrapper sourceWrapper = new BeanWrapperImpl(src);
        BeanWrapper targetWrapper = new BeanWrapperImpl(target);

        // Loop through the properties of the source object
        for (PropertyDescriptor pd : sourceWrapper.getPropertyDescriptors()) {
            // If the property is not null and the target object has a writable property with the same name
            Object srcValue = sourceWrapper.getPropertyValue(pd.getName());

            System.out.println("srcValue: " + pd.getName() + " : " + srcValue);
            if (srcValue != null && targetWrapper.isWritableProperty(pd.getName())) {
                targetWrapper.setPropertyValue(pd.getName(), srcValue);
            }
        }
    }
}
