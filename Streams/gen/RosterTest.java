package gen;

/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class RosterTest {
  private static final String SELECTIVE_SERVICE =
      "Persons who are eligible for Selective Service ";

  private RosterTest() {
    throw new IllegalStateException("Private constructor");
  }

  // Approach 1: Create Methods that Search for Persons that Match One
  // Characteristic
  // clang-format off
  public static void printPersonsOlderThan(List<Person> roster, int age) { // clang-format off
    for (Person p : roster) {
      if (p.getAge() >= age) {
        p.printPerson();
      }
    }
  }

  // Approach 2: Create More Generalized Search Methods
  // clang-format off
  public static void printPersonsWithinAgeRange(
      List<Person> roster, int low, int high) {  // clang-format on
    for (Person p: roster) {
      if (low <= p.getAge() && p.getAge() < high) {
        p.printPerson();
      }
    }
  }

  // Approach 3: Specify Search Criteria Code in a Local Class
  // Approach 4: Specify Search Criteria Code in an Anonymous Class
  // Approach 5: Specify Search Criteria Code with a Lambda Expression
  // clang-format off
  public static void printPersons(List<Person> roster, CheckPerson tester) {  // clang-format on
    for (Person p: roster) {
      if (tester.test(p)) {
        p.printPerson();
      }
    }
  }

  // Approach 6: Use Standard Functional Interfaces with Lambda Expressions
  // clang-format off
  public static void printPersonsWithPredicate(
      List<Person> roster, Predicate<Person> tester) {  // clang-format on
    for (Person p: roster) {
      if (tester.test(p)) {
        p.printPerson();
      }
    }
  }

  // Approach 7: Use Lambda Expressions Throughout Your Application
  // clang-format off
  public static void processPersons(
      List<Person> roster, Predicate<Person> tester, Consumer<Person> block) {  // clang-format on
    for (Person p: roster) {
      if (tester.test(p)) {
        block.accept(p);
      }
    }
  }

  // Approach 7, second example
  public static void processPersonsWithFunction(List<Person> roster,
                                                Predicate<Person> tester,
                                                Function<Person, String> mapper,
                                                Consumer<String> block) {
    for (Person p: roster) {
      if (tester.test(p)) {
        String data = mapper.apply(p);
        block.accept(data);
      }
    }
  }

  // Approach 8: Use Generics More Extensively
  public static <X, Y> void processElements(Iterable<X> source,
                                            Predicate<X> tester,
                                            Function<X, Y> mapper,
                                            Consumer<Y> block) {
    for (X p: source) {
      if (tester.test(p)) {
        Y data = mapper.apply(p);
        block.accept(data);
      }
    }
  }

  @SuppressWarnings({"PMD.ExcessiveMethodLength", "PMD.LawOfDemeter"})
  public static void main(String... args) {
    List<Person> roster = Person.createRoster();

    for (Person p: roster)
      p.printPerson();

    // Approach 1: Create Methods that Search for Persons that Match One
    // Characteristic
    System.out.println("Persons older than 20:");
    printPersonsOlderThan(roster, 20);
    System.out.println();

    // Approach 2: Create More Generalized Search Methods
    System.out.println("Persons between the ages of 14 and 30:");
    printPersonsWithinAgeRange(roster, 14, 30);
    System.out.println();

    // Approach 3: Specify Search Criteria Code in a Local Class
    System.out.println(SELECTIVE_SERVICE + ":");

    class CheckPersonEligibleForSelectiveService implements CheckPerson {
      @Override
      public boolean test(Person p) {
        return p.getGender() == Person.Sex.MALE && p.getAge() >= 18
            && p.getAge() <= 25;
      }
    }

    printPersons(roster, new CheckPersonEligibleForSelectiveService());

    System.out.println();

    // Approach 4: Specify Search Criteria Code in an Anonymous Class
    System.out.println(SELECTIVE_SERVICE + "(anonymous class):");

    printPersons(roster, new CheckPerson() {
      @Override
      public boolean test(Person p) {
        return p.getGender() == Person.Sex.MALE && p.getAge() >= 18
            && p.getAge() <= 25;
      }
    });

    System.out.println();

    // Approach 5: Specify Search Criteria Code with a Lambda Expression
    System.out.println(SELECTIVE_SERVICE + "(lambda expression):");

    printPersons(roster,
                 (Person p)
                     -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18
                            && p.getAge() <= 25);

    System.out.println();

    // Approach 6: Use Standard Functional Interfaces with Lambda
    // Expressions
    System.out.println(SELECTIVE_SERVICE + "(with Predicate parameter):");

    printPersonsWithPredicate(roster,
                              p
                              -> p.getGender() == Person.Sex.MALE
                                     && p.getAge() >= 18 && p.getAge() <= 25);

    System.out.println();

    // Approach 7: Use Lamba Expressions Throughout Your Application
    System.out.println(SELECTIVE_SERVICE
                       + "(with Predicate and Consumer parameters):");

    processPersons(roster,
                   p
                   -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18
                          && p.getAge() <= 25,
                   p -> p.printPerson());

    System.out.println();

    // Approach 7, second example
    System.out.println(
        SELECTIVE_SERVICE
        + "(with Predicate, Function, and Consumer parameters):");

    processPersonsWithFunction(roster,
                               p
                               -> p.getGender() == Person.Sex.MALE
                                      && p.getAge() >= 18 && p.getAge() <= 25,
                               p
                               -> p.getEmailAddress(),
                               email -> System.out.println(email));

    System.out.println();

    // Approach 8: Use Generics More Extensively
    System.out.println(SELECTIVE_SERVICE + "(generic version):");

    processElements(roster,
                    p
                    -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18
                           && p.getAge() <= 25,
                    p
                    -> p.getEmailAddress(),
                    email -> System.out.println(email));

    System.out.println();

    // Approach 9: Use Bulk Data Operations That Accept Lambda Expressions
    // as Parameters
    System.out.println(SELECTIVE_SERVICE + "(with bulk data operations):");

    roster.stream()
        .filter(p
                -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18
                       && p.getAge() <= 25)
        .map(p -> p.getEmailAddress())
        .forEach(System.out::println);
  }

  interface CheckPerson {
    boolean test(Person p);
  }
}
