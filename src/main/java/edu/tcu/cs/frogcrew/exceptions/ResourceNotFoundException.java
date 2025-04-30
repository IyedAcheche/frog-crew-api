package edu.tcu.cs.frogcrew.exceptions;

// Custom exception for when resources (like crew members, games) can't be found
// Spring will convert this to a 404 Not Found response
public class ResourceNotFoundException extends RuntimeException {
    // Constructor takes the resource type and its identifier (like ID or email)
    public ResourceNotFoundException(String resource, Object identifier) {
        super(resource + " not found: " + identifier);
    }
}
