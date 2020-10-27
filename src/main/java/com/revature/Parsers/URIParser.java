package com.revature.Parsers;

public class URIParser {
    public static String getLastSegment(String toParse) {
        String[] pathSegments = toParse.split("/");
        return pathSegments[pathSegments.length -1];
        //If I'm understanding right, this will grab the whole length of a URI, like www.swapi.net/people/1/
        //And split that into www.swapi.net, people, 1, and then just an empty "".  
        //And we want that second to last, so we do -1, which should just return "1" 
    }
}
