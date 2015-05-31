(ns problems.152)

(def t-sq '[[A B C]
            [B C A]
            [C A B]])

(def f-sq '[[A B C]
            [B C A]
            [C A C]])

(def d-sq '[[A B C]
            [B D A]
            [C A B]])

; check if a given square is a valid latin square
(defn latin? [sq]
  (apply = (concat (map set sq) (apply map hash-set sq))))

(assert (latin? t-sq))
(assert (not (latin? f-sq)))
(assert (not (latin? d-sq)))
