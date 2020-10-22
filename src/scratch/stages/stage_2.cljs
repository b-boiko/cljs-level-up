(ns scratch.views.stages.stage-2
  (:require [re-frame.core             :as rf]
            [scratch.subs              :as subs]
            [scratch.util              :refer [pad]]
            [scratch.views.common.card :refer [card]]))

(defn first-number
  []
  [card
   :title "First Number"
   :text  "This shows the first number in a sequence using the clojure `first` function."
   :body  @(rf/subscribe [::subs/first-number])])

(defn my-first-number
  []
  [card
   :title "Custom First Number"
   :text  "This shows the first number in a sequence using a custom function."
   :body  @(rf/subscribe [::subs/my-first-number])])

(defn last-number
  []
  [card
   :title "Last Number"
   :text  "This shows the last number in a sequence using the clojure `last` function."
   :body  @(rf/subscribe [::subs/last-number])])

(defn my-last-number
  []
  [card
   :title "Custom Last Number"
   :text  "This shows the last number in a sequence using a custom function."
   :body  "TODO: Implement the subscription and body for this card."])

(defn second-number
  []
  [card
   :title "Second Number"
   :text  "This shows the second number in a sequence using the clojure `second` function."
   :body  @(rf/subscribe [::subs/second-number])])

(defn my-second-number
  []
  [card
   :title "Custom Second Number"
   :text  "This shows the second number in a sequence using a custom function."
   :body  "TODO: Implement the subscription and body for this card. If you can, try to solve this using a different strategy than you used to solve the two previous exercises."])

(defn rest-numbers
  []
  [card
   :title "Rest Numbers"
   :text  "This shows everything but the first number in a sequence using the clojure `rest` funciton."
   :body  (pad @(rf/subscribe [::subs/rest-numbers]))])

(defn my-rest-numbers
  []
  [card
   :title "Custom Rest Numbers"
   :text  "This shows everything but the first number in a sequence using a custom function."
   :body  "TODO: Implement the subscription and body for this card."])

(defn first-10-numbers
  []
  [card
   :title "First 10 Numbers"
   :text  "This will show only the first 10 numbers"
   :body  "TODO: Implement the subscription and body for this card. If you need a hint, try the following search query in your favorite search engine `clojure get first n items`."])

(defn last-10-numbers
  []
  [card
   :title "Last 10 Numbers"
   :text  "This will show only the last 10 numbers"
   :body  "TODO: Implement the subscription and body for this card. If you need a hint, try a similar search query from the question above."])

(defn my-even-numbers
  []
  [card
   :title "My Odd Numbers"
   :text  "This will show only the odd numbers using a custom function."
   :body  "TODO: Implement the subscription and body for this card without using `filter`. We can assume the numbers are in order and steadily increasing for this problem. If you need some ideas, try looking at `partition` or `take-nth` in the clojure docs, but don't feel limited to those."])

(defn my-odd-numbers
  []
  [card
   :title "My Odd Numbers"
   :text  "This will show only the odd numbers using a custom function."
   :body  "TODO: Implement the subscription and body for this card without using `filter`. We can assume the numbers are in order and steadily increasing for this problem. If you need some ideas, try looking at `partition` or `take-nth` in the clojure docs, but don't feel limited to those."])

(defn single-digit-numbers
  []
  [card
   :title "Single Digit Numbers"
   :text  "This will show only the single digit numbers using a custom function."
   :body  "TODO: Implement the subscription and body for this card. Another way to think of a single digit number is any number under 10. If you need a hint, try this search query in your favorite search engine `clojure get items and stop`."])

(defn stage
  []
  [:<>
   [:h3 "Stage 2 --"]
   [:p "This stage will focus on sequences in Clojure, both their structure and functions that operate on them. We'll look at core functions as well as get creative and replicate a bunch of them. A side story of this is to also focus on research skills, being able to search the internet and clojure docs for examples that might help out."]
   [:p "Sequences make up a crucial component of Clojure data structures, and you can read up some more here in the "
    [:a {:href "https://clojure.org/reference/sequences"} "official docs"]
    " and a more detailed "
    [:a {:href "https://insideclojure.org/2015/01/02/sequences/"} "write up by Alex Miller"] "."]
   [:p "The super simple idea is they are an ordered collection of things (whatever you want) that is optimized for sequential navigation. That means going from the first item to the last is super easy, but going specifically to the 22nd thing, not so much. This means operations like getting a item (or items) of the top is simple, and there are many core functions to do so."]
   [:p "In this stage we will touch on a few different ways we can interact with sequences. This is not a complete list, and is meant to be the touching off point for more research."]
   [:h4 "Scalar operations"]
   [:p "A number of functions we can use on sequences will return a single item. Things like getting the first item, the last item, the second item, the nth item, so on and so forth. We will only cover a few here, and also take some time to reimplement some of the solutions."]
   [:h4 "First (Already completed, use the code for reference)"]
   [first-number]
   [my-first-number]
   [:h4 "Last"]
   [last-number]
   [my-last-number]
   [:h4 "Second"]
   [second-number]
   [my-second-number]
   [:h4 "Sequence operations"]
   [:p "Many functions on sequences will return new sequences, that usually return a smaller version of the original sequence. This will again only cover a few examples."]
   [:h4 "Rest"]
   [rest-numbers]
   [my-rest-numbers]
   [:h4 "Custom Filters"]
   [first-10-numbers]
   [last-10-numbers]
   [my-even-numbers]
   [my-odd-numbers]
   [single-digit-numbers]])
