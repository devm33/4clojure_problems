(ns problems.67)

; first n prime numbers
; sneaky regex version ;]

(defn __ [n]
  (take
    n
    (filter
      #(empty? (re-matches #"^1?$|^(11+?)\1+$" (apply str (repeat % "1"))))
      (range))))

(__ 10)
