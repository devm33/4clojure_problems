(ns problems.106)

; number maze - find lenght of shortest path from s to t
; valid moves:
; - double
; - halve
; - add 2

; simple bfs approach
; not perf tuned at all

(defn __ [s t]
  (loop [que [[s 1]]]
    (when-let [[c l] (last que)]
      (if (= t c) l
        (recur (conj (butlast que)
                     [(* c 2) (inc l)]
                     [(/ c 2) (inc l)]
                     [(+ c 2) (inc l)]))))))


(assert (= 1 (__ 1 1)))  ; 1

(assert (= 3 (__ 3 12))) ; 3 6 12

(assert (= 3 (__ 12 3))) ; 12 6 3

(assert (= 3 (__ 5 9)))  ; 5 7 9

(assert (= 9 (__ 9 2)))  ; 9 18 20 10 12 6 8 4 2

(assert (= 5 (__ 9 12))) ; 9 11 22 24 12
