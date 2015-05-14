(ns problems.195)

(defn __ [n]
  (if (zero? n) #{""}
    (set (mapcat #(list (str \( % \)) (str % "()") (str "()" %))
                 (__ (dec n))))))

(assert (= #{"((()))" "()()()" "()(())" "(())()" "(()())"} (__ 3)))

(count (__ 10))

(count (__ 7))
