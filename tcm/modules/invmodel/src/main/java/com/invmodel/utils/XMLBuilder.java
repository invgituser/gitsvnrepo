package com.invmodel.utils;

/**
 * Created with IntelliJ IDEA.
 * User: SG0211412
 * Date: 6/6/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLBuilder {
    public static String buildAllocationInput(String age, String risk, String duration) {
        return buildElement("AllocationInput", buildElement("Age", age) + buildElement("Risk", risk) + buildElement("Duration", duration));
    }

    public static String buildElement(String tag, String text) {
        return "<" + tag + ">" + text + "</" + tag + ">";
    }
}
