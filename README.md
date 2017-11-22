# Incremental TDD
A simple project to share ideas about TDD.

TDD which means [Test Driven Development](https://en.wikipedia.org/wiki/Test-driven_development) is a software development process in which the tests are written before the implementation.

## Structure

The project has 2 main packages:

 - diytdd - a test where you should write the implementation for.
 - incrementaltdd - a set of classes with good and bad practices for testing.

### DIY TDD

This is a sort of "Do It Yourself". You can clone the project, remove the `@Ignore` from `ExpressionEvaluatorServiceTest` and start implementing stuff. The point of this test is to show how easy and fun it is to code with TDD. It is really good for beginners who never did TDD and this is their first encounter with the concept.

While implementing this `ExpressionEvaluatorService`, please try to stick to Ward Cunningham's "[Do The Simplest Thing That Could Possibly Work](http://c2.com/xp/DoTheSimplestThingThatCouldPossiblyWork.html)" principle. It's harder than it seems. Try to fix only one unit test at a time. Do not come up with a perfect solution.

As more tests will pass, chances are you will start to see repetition. Here is where the fun part starts: try to follow the [Red-Green-Refactor](http://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html) cycle. This will show you how much freedom to refactor TDD gives developers.

Bonus: you can add extra test methods and then implement them in order to offer more features to `ExpressionEvaluatorService`.

### Incremental TDD

This package is split into 2 parts:

 - before
 - after

The purpose of this package is to show some common code smell that make TDD impossible. There are a lot more, but these are just a good starter.