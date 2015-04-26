(ns problems.27)

(defn palindrome [col]
  (if (< (count col) 2)
    true
    (let [s (seq col)]
       (if (= (first s) (last s))
         (recur (drop-last (rest s)))
         false))))

(palindrome "racecar")