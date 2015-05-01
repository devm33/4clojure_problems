(ns problems.70)

(defn __ [s]
  (sort
    (fn [& args]
      (apply compare (map clojure.string/lower-case args)))
    (clojure.string/split
      (clojure.string/replace s #"[\.\!]" " ")
      #"\s+")))


(__ "Have a nice day.")

(assert (= (__  "Have a nice day.")
           ["a" "day" "Have" "nice"]))

(assert (= (__  "Clojure is a fun language!")
           ["a" "Clojure" "fun" "is" "language"]))

(assert (= (__  "Fools fall for foolish follies.")
           ["fall" "follies" "foolish" "Fools" "for"]))
