package cl.qs.securitycoreserver.util;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

import org.springframework.data.domain.ExampleMatcher;

public class Matcher {

  public static ExampleMatcher getLevelMatcher() {
    return ExampleMatcher
        .matchingAll()
        .withMatcher("name", contains().ignoreCase());
  }

}
