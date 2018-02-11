# TDD Intro
A simple project to share ideas about TDD.

TDD which means [Test Driven Development](https://en.wikipedia.org/wiki/Test-driven_development) is a software development process in which the [tests are written before the implementation](https://vimeo.com/255248348).

## Structure

The project has 2 main packages:

 - diytdd - a test where you should write the implementation for.
 - incrementaltdd - a set of classes with good and bad practices for testing.

### 1. DIY TDD

This is a sort of "Do It Yourself". You can clone the project, remove the `@Ignore` from `ExpressionEvaluatorServiceTest` and start implementing stuff. The point of this test is to show how easy and fun it is to code with TDD. It is really good for beginners who never did TDD and this is their first encounter with the concept.

While implementing this `ExpressionEvaluatorService`, please try to stick to Ward Cunningham's "[Do The Simplest Thing That Could Possibly Work](http://c2.com/xp/DoTheSimplestThingThatCouldPossiblyWork.html)" principle. It's harder than it seems. Try to fix only one unit test at a time. Do not come up with a perfect solution.

As more tests will pass, chances are you will start to see repetition. Here is where the fun part starts: try to follow the [Red-Green-Refactor](http://blog.cleancoder.com/uncle-bob/2014/12/17/TheCyclesOfTDD.html) cycle. This will show you how much freedom to refactor TDD gives developers.

Bonus: you can add extra test methods and then implement them in order to offer more features to `ExpressionEvaluatorService`. Basically continue the development in TDD style just to get used to it.

### 2. Incremental TDD

This package is split into 2 sub packages:

 - before
 - after
 
The purpose of this package is to show some common code smell that make TDD impossible. There are a lot more, but these are just a good starter.

It contains some simple async code samples. Just look at `HelloServiceTest`, `PrivateInfoDivCreatorServiceTest` and `HelloPersonServletTest`.
Try to look only at the test and understand what it is suppose to do. Ignore the implementation. Do this with the `after` package wich is the "refactored" version and then look at the `before` package.
The code in both "before" and "after" does the same thing. Now imagine you are going to start writing a new test. In which one would it be simpler? Before or after?
You can see that the "after" implementation is pretty hard to understand also.

## Let's see what makes the code in the "after" a lot more testable:
 
 - Method input is mostly in it's parameters, not injected.
 
 - If it needs some global state, it does not inject it. `HelloService` uses a service that exposes a method: `EnvironmentService`. 
 Uncle Bob would say that `EnvironmentService` is an object and `Environment` is a data structure (see "Data/Object Anti-Symmetry" from Clean Code)
 
 - Method output is always in it's return object. Parameters are not used to communicate with the caller. Compare `HelloService<String>` with `Callback<String>`.
 
 - Everything happening inside the method is encoded in the result. It's easy to get the inner object of a `CompletableFuture` and see if it has a field set or not.
 You can think this in advance. Basically the `PrivateInfoDivCreatorServiceTest` is like a magician that says: for my next trick I will implement a method that will return a `<div>JANE DOE - 123456789</div>`.
 
 - The async code looks as if it is synchronous. `PrivateInfoDivCreatorService` first gets the social social security number, then the full name and at the end it does something with both.
 
## What makes tests easier to write:

 - The tests always check state, not behaviour. Avoid verify and argument captors.
 
 - The tests do not mock state, they use real data for state.
 
 - They only mock behaviour. In `HelloService` It's super easy to say "I will have a EnvironmentService that will return the server name, or absent.".
 If you inject your class with an `Environment` you will have no clue what nested field the implementation will use. [Don't look for things](https://www.youtube.com/watch?v=RlfLCWKxHJ0&feature=youtu.be&t=583)
 
 - Avoid ugly APIs. Ugly APIs make classes which have state hard to instantiate. This pushes people to mock them. Avoid this. Wrap the API object inside a class of your own that is easy to construct.
 Xml Element class is a good example that is hard to construct and hard to clone. Try to use a wrapper on top of it that exposes utility methods.