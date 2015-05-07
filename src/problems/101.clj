(ns problems.101)

; levenshtein distance (edit distance)

(defn __ [a b]
  (last
    (reduce
      (fn [prevrow j]
        (reductions
          (fn [prev i]
            (min (inc prev) (inc (nth prevrow i))
                 (+ (nth prevrow (dec i)) 
                    (if (= (nth a (dec i)) (nth b (dec j))) 0 1))))
          j (range 1 (inc (count a)))))
      (range (inc (count a))) (range 1 (inc (count b))))))


(assert (= (__ "kitten" "sitting") 3))

(assert (= (__ "closure" "clojure") (__ "clojure" "closure") 1))

(assert (= (__ "xyx" "xyyyx") 2))

(assert (= (__ "" "123456") 6))

(assert (= (__ "Clojure" "Clojure") (__ "" "") (__ [] []) 0))

(assert (= (__ [1 2 3 4] [0 2 3 4 5]) 2))

(assert (= (__ '(:a :b :c :d) '(:a :d)) 2))

(assert (= (__ "ttttattttctg" "tcaaccctaccat") 10))

(assert (= (__ "gaattctaatctc" "caaacaaaaaattt") 9))
