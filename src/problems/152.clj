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

(def ft-sq '[[A B C D]
             [B A D C]
             [D C B A]
             [C D A B]])

; check if a given square is a valid latin square
(defn latin? [sq]
  (apply = (concat (map set sq) (apply map hash-set sq))))

(assert (latin? t-sq))
(assert (not (latin? f-sq)))
(assert (not (latin? d-sq)))


; return the number of latin subsquares of size n
(defn nsubsqs [sq n]
  (if (= n (count sq))
    (if (latin? sq) 1 0)
    (->>
      (for [r (range (- (count sq) n))
            c (range (- (count sq) n))]
        (map #(take n %) (take n sq)))
      (filter latin?)
      count)))

(assert (= 1 (subsqs ft-sq 4)))
(assert (= 4 (subsqs ft-sq 2)))

; return a mapping of sizes to counts of latin subsquares
(defn sqmap [sq]
  (->>
    (range 2 (inc (count sq)))
    (map #(vector % (nsubsqs sq %)))
    (filter #(-> % second (not= 0)))
    (into {})))

(sqmap ft-sq)
