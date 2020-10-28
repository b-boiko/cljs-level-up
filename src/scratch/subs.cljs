(ns scratch.subs
  (:require [re-frame.core :refer [reg-sub]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Subs introduced in Stage 1
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; This subscription will return the :numbers from our database.
(reg-sub
 ::all-numbers
 (fn [db _]
   (:numbers db)))

;; This subscription will consume the ::all-numbers and return only the odd ones.
(reg-sub
 ::odd-numbers
 :<- [::all-numbers]
 ;; The above is syntactic sugar that tells this subscription to consume the
 ;; output of the ::all-numbers subscription to use as the input for ::odd-numbers.
 ;; Re-frame performs aggressive caching of sub outputs, so reusing our subs
 ;; in this manner provides us incredible performance benefits.
 #_(fn [_ _]
     @(re-frame.core/subscribe [::all-numbers]))
 (fn [all-numbers _]
   ;; Take only the odd numbers from our db
   (filter odd? all-numbers)))

;; This subscription will consume the ::all-numbers and return only the even ones.
;; Refer to the notes above for the inner details.
(reg-sub
 ::even-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (filter even? all-numbers)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Subs introduced in Stage 2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; This sub uses the clojure `first` function to get
;; the first item in a sequence.
(reg-sub
 ::first-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (first all-numbers)))

;; This subscription uses another function to do the same thing, just with
;; a little more work
;; We use `get` to get the item in the 0th index, or first position.
(reg-sub
 ::my-first-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (get all-numbers
        0)))

;; This sub uses the clojure `last` function to get
;; the last item in a sequence.
(reg-sub
 ::last-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (last all-numbers)))

;; This sub will get the last item of a collection by getting the item
;; in the last index position.
;; If there is a collection of `n` items, then the index of the last item
;; is at `n - 1`. We can find `n` by getting the `count` of our collection.
(reg-sub
 ::my-last-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (get all-numbers
        ;; n - 1, where n is the count of the collection
        ;; represents the last index
        (dec (count all-numbers)))))

(reg-sub
 ::second-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   (second all-numbers)))

;; This sub will get the second item in a extra roundabout way
;; We can think of the second item as the first item from the rest
;; of the data. I didn't want to use `first` or `get` since I used it already.
;; I went with `peek` but it will not work on a lazy seq, so I have to coerce
;; my data into a list so `peek` will data the value from the front.
(reg-sub
 ::my-second-number
 :<- [::all-numbers]
 (fn [all-numbers _]
   ;; for `peek` to take the first item, we need a list. If we had a vector
   ;; it would actually grab the last item, which is not what we want.
   ;; `(apply list ...)` will coerce our lazy seq into a list so `peek` is happy.
   (peek (apply list
                ;; using `rest` will basically drop the first item,
                ;; meaning the original second item is the "new" first item
                (rest all-numbers)))))

;; This sub uses the clojure `rest` function to get
;; everything but the first item.
(reg-sub
 ::rest-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (rest all-numbers)))

;; This sub uses the `drop` function to simply drop the first item
;; which is synonymous with returning the rest.
(reg-sub
 ::my-rest-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (drop 1 all-numbers)))

;; This sub uses the `take` function to take the first 10 items
;; from the collection.
(reg-sub
 ::my-first-10-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (take 10 all-numbers)))

;; This sub uses the `take-last`, a sibling of the function used above
;; to grab the last 10 items.
(reg-sub
 ::my-last-10-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (take-last 10 all-numbers)))

;; This sub is an over complicated solution, and only works because
;; we know all of the numbers are in order AND steadily increase by 1.
;; We use `partition` to create numerous partitions of our data. By default,
;; `partition` steps through every single item in your collection.
;; You can override that step however, as I do below. I create partitions
;; of size 1, and I step by 2, meaning I get every other number. Since even
;; numbers will be every other number in an ordered, increasing by 1 collection,
;; this logic works. All I need to do is know if the first number is even or odd,
;; and then partition accordingly. Finally, I `flatten` the results to make one
;; list instead of a list of lists.
(reg-sub
 ::my-even-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   ;; I partition the data in groups of 1, and with a step of 2.
   ;; Finally I `flatten` to bring all the data together
   (flatten (partition 1 2
                       ;; I need to know the first number. If it is even, we
                       ;; can partition starting here and the steps are right.
                       ;; otherwise the first number is odd, and I can just grab
                       ;; the `rest` of the data, meaning it now starts
                       ;; on an even number
                       (if (even? (first all-numbers))
                         all-numbers                 
                         (rest all-numbers))))))

;; This sub is also overcomplicated, and I solved it using a different pattern
;; than the one above. This time I use `take-nth`, which lets you take every nth
;; item in a collection. This can be done to achieve the same idea as a partition
;; to take every other number if I set the nth step to be 2. The result is a list
;; of numbers so this time I do not need to flatten.
;; The same step to determine the first number is taken here, just this time we
;; check to see if the first number is odd
(reg-sub
 ::my-odd-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (take-nth 2 (if (odd? (first all-numbers))
                 all-numbers                 
                 (rest all-numbers)))))

;; Yet another severely overcomplicated solution, but I really wanted to try a
;; solution that used `take-while` so I made it happen.
;; `take-while` is a sibling to `take` that will continue to take things from
;; a collection `while` some predicate is evaluating to true. The first time
;; it becomes false, it stops consumption. This is different from `filter`
;; because `filter` will keep going until the end of the collection, so this is
;; a bit of a short circuit that is useful to our use case, of stopping at some
;; bounds. I also used `drop-while` which does the opposite, it will drop items
;; from a collection while a predicate is true. We use both of these to basically
;; generate two sets.
;; We get `set_a` which is the set of the numbers greater than or equal to -9
;; using `drop-while`
;; We get `set_b` which is the set of the numbers less than or equal to 9
;; using `take-while`
;; Using `filter`, we can get the intersection of these two collections, which
;; will only return the numbers between [-9, 9].
;; Obviously this is overkill, the simplest solution is to have a filter with
;; the predicate #(< -10 % 10) and we'd be done.
(reg-sub
 ::my-single-numbers
 :<- [::all-numbers]
 (fn [all-numbers _]
   (filter (set (drop-while #(> -9 %)
                            all-numbers))
           (take-while #(> 10 %)
                       all-numbers))))

(comment
  ;; This is a simpler approach which just create a set of number from [-9, 9]
  ;; and then performs a set intersection on all-numbers. When we use a set
  ;; in the place of a function, it acts like `(contains? #{1 2 3} 1)
  (keep (set (range -9 10))
        (vec (range -20 20)))

  ;; This is the most efficient, simple solution
  (filter #(< -10 % 10)
          (range -20 20)))
