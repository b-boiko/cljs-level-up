# cljs-level-up
A level up repo with lessons on cljs, primarily focusing on reagent, re-frame, and core CLJS features.

## How to use this

This repo will be set up using git tags. Each stage will be released with a tag (like `stage-1`) and a solution to it with notes will also be provided (like `stage-1-solution`). The most useful way to use this repo is to pull a tag, follow any instructions in the README and code, and then check your solution to the supplied solution.

Please use the issues tab to supply corrections, questions, or alternate solutions to any of the prompts. Please try to be as specific as you can to the tag, question, and file.

This README will contain notes, instructions, and helpers for each stage.

## Stage 1 - Getting comfortable with ClojureScript projects, directory setup, and some basic reagent/re-frame.

This section will talk briefly about project setups, as well as run through some basic reagent/re-frame patterns to establish good habits.

### Running the App

There are numerous ways to start this application. The most basic is to use the `clojure` or `clj` command line tool to start this application. The `deps.edn` file, `dev.deps.edn`, and `figwheel-main.edn` file are crucial to running this project.

* deps.edn
  * This is the core file to a Clojure project (if you are using the deps-cli pattern. Lein and boot are alternatives that we won't be covering).
  * This file explicitly defines the project dependencies, any extra resources, and finally any aliases, or command line shortcuts to run a clojure program.
    * We have one alias, `fig` and the purpose is to run figwheel in development mode. Figwheel compiles our CLJS code in JS and also powers hot-reloading of our code for rapid development.
* dev.cljs.edn
  * When running Figwheel, we can specify different modes we might want to operate in (dev, prod, stage, test, ...).
  * This sets our options for development mode, and gives Figwheel some extra options.
* figwheel-main.edn
  * This sets some global figwheel options.
  
To run our application via clj-cli tools, we can do either of the following:

```
clojure -A:fig

OR

clj -A:fig
```

A Makefile is also provided for more customization and future automation. Three targets are provided:

* clean
  * This will remove the `target/` directory which has all the compiled code.
* run-dev
  * This will run the clj-cli command above for us.
* run-fresh
  * This will run the two targets above to us.
  
Optionally, if you use Cider, or some other editor nrepl tool, you can run it there as well. The REPL type is `figwheel-main` and the build is `dev`.

Whatever path you choose, the app will pull dependencies, compile the code, and open a tab in your browser and you will see the app.

### The Code

All source code lives in `src/scratch` in `.cljs` files. The breakdown here is as follows:

* `core.cljs`
  * Entry point into the application.
* `db.cljs`
  * Database schema and initial values.
* `events.cljs`
  * Re-frame events and interceptors.
* `subs.cljs`
  * Re-frame subscriptions.
* `views.cljs`
  * All of our view components.
* `util.cljs`
  * Any functions that might be used in our views.
* `common\`
  * This is a directory that is used for common components throughout the app.
  * I will not enumerate all of the components as this will grow over time.
* `stages\`
  * This is the directory that will try to partition all of the stages into their own namespaces.
  * It'll be impossible to silo off every exercise, but for the most part, each stage will have it's own file.
  
### The Tasks

For this stage, a default database, a simple view, and a subscription have already been implemented for you. Review the code and any TODOs within.

Most notes have already been provided in line in the application and via comments, so please use that as a resource. This is just a concise list of the tasks.

You will need to only focus on creating subscriptions and some view code for this. So all your work can be done in `subs.cljs` and `stages/stage_2.cljs`.

The tasks for this stage are:

#### First

This one has already been completed to give some idea of the structure and directions to take.

#### Last

The first has already been implemented using `last` and your task is to create another sub that will provide the same result.

#### Second

The first has already been implemented using `second` and your task is to create another sub that will provide the same result.

#### Rest

The first has already been implemented using `rest` and your task is to create another sub that will provide the same result.

#### Extra Challenges

There are five more exercises after that will require a bit of research, and some search query terms have been provided. The goal here is to get a bit more fluent with using clojure docs, stack overflow, and just plain old google searches to find solutions to Clojure problems. There is more than one way to solve all of these problems so don't feel constrained to use to provided hints.
