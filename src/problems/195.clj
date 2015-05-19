(ns problems.195)

(defn __ [n]
  (if (zero? n) #{""}
    ((fn br [o c v]
       (if (zero? o)
         (if (zero? c) #{}
           #{(apply str (apply conj v (take c (repeat \)))))})
         (loop [cc c lv v vs #{}]
           (if (< cc 0) vs
             (recur
               (dec cc)
               (conj lv \))
               (apply conj vs (br (dec o) (inc cc) (conj lv \())))))))
     n 0 [])))


(assert (= #{"((()))" "()()()" "()(())" "(())()" "(()())"} (__ 3)))
