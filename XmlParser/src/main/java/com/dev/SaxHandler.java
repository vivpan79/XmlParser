package com.dev;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivek_000 on 2015-11-26.
 */
public class SaxHandler extends DefaultHandler{
    public static final String ECO = "ECO";
    public static final String DEVIATING_TIME = "DEVIATING_TIME";
    public static final String GENERAL_TIME = "GENERAL_TIME";
    public static final String OAS_VALUE = "oas:value";
    public static final String OBJECT = "object";
    public static final String TYPE = "type";
    public static final String ENV_CONTENT = "env:content";
    boolean object = false;
    boolean oasValue = false;
    boolean eco = false;
    boolean generalTime = false;
    boolean deviatingTime = false;
    private final List<TimeCarrierBuilder> timeCarrierBuilders;
    private int counter;

    public SaxHandler() {
        timeCarrierBuilders = new ArrayList<TimeCarrierBuilder>();
        counter = -1;
    }

    @Override
    public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(ENV_CONTENT)) {
            //TODO Start an Array
        }
        if (qName.equalsIgnoreCase(OBJECT)) {
            object = true;
            final String attributesValue = attributes.getValue(TYPE);
            if (attributesValue.equalsIgnoreCase(ECO)) {
                eco = true;
                //System.out.println("ATTRIBUTES_VALUE::ECO");
                //TODO finalize prev time_carrier
                if(counter >= 0) {
                    final TimeCarrier timeCarrier = timeCarrierBuilders.get(counter).getTimeCarrier();
                    System.out.println("TimeCarrier:" + timeCarrier);
                }
                counter++;
                //TODO start new time_carrier
                timeCarrierBuilders.add(new TimeCarrierBuilder());
            }
            if (attributesValue.equalsIgnoreCase(DEVIATING_TIME)) {
                deviatingTime = true;
                //System.out.println("ATTRIBUTES_VALUE::DEVIATING_TIME");
            }
            if (attributesValue.equalsIgnoreCase(GENERAL_TIME)) {
                generalTime = true;
                //System.out.println("ATTRIBUTES_VALUE::GENERAL_TIME");
            }
        }
        if (qName.equalsIgnoreCase(OAS_VALUE)) {
            oasValue = true;
        }

        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (object) {
            object = false;
        }

        if (oasValue) {
            oasValue = false;
            if(eco){
                //System.out.println("TYPE::ECO:" + new String(ch, start, length).trim());
                eco = false;
                timeCarrierBuilders.get(counter).setEco(new String(ch, start, length).trim());
            }
            if(deviatingTime){
                //System.out.println("TYPE::DEVIATING_TIME:" + new String(ch, start, length).trim());
                deviatingTime = false;
                timeCarrierBuilders.get(counter).setDeviatingTime(new String(ch, start, length).trim());
                //TODO add deviatingTime to time_carrier
            }
            if(generalTime){
                //System.out.println("TYPE::GENERAL_TIME:" + new String(ch, start, length).trim());
                generalTime = false;
                timeCarrierBuilders.get(counter).setGeneralTime(new String(ch, start, length).trim());
                //TODO add generalTime to time_carrier
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase(ENV_CONTENT)) {
            oasValue = true;
            System.out.println("End Element :" + qName);
        }
    }

    public List<TimeCarrier> getTimeCarriers() {
        final List<TimeCarrier> timeCarriers = new ArrayList<TimeCarrier>();
        for (TimeCarrierBuilder timeCarrierBuilder : timeCarrierBuilders) {
            timeCarriers.add(timeCarrierBuilder.getTimeCarrier());
        }
        return timeCarriers;
    }
}
