(ns problems.34)

(defn myrange [start stop]
  (take (- stop start) (iterate inc start)))

(myrange 1 4)