(ns problems.127)

; if n consecutive then
(comment
  (quot (* n (inc n)) 2))

; if a is the nth consecutive then
(comment
  (let [nmask (dec (bit-shift-left 1 n))] (= nmask (bit-and nmask a))))

(def __
  (letfn [(sumn [n] (quot (* n (inc n)) 2))
          (consec ([s] (consec s 0))
            ([s n] (if (empty? s) n
                     (let [nm (dec (bit-shift-left 1 (inc n)))]
                       (if (not= nm (bit-and nm (first s))) n
                         (recur (rest s) (inc n)))))))]
    (fn [s] (sumn (max (consec s) (consec (reverse s)))))))


(assert (= 10 (__ [15 15 15 15 15])))
(assert (= 15 (__ [1 3 7 15 31])))
(assert (= 15 (__ [1 3 7 15 31])))
(assert (= 4 (__ [7 3])))
; 111      ***
; 011  ->  0*1
