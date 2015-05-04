(ns problems.77)

; note, doesn't account for repetition of letters
(defn __ [strs]
   (set (filter #(> (count %) 1) (map set (vals (group-by set strs))))))


(__ ["asdf" "fdas"])


(assert (= (__ ["meat" "mat" "team" "mate" "eat"])
   #{#{"meat" "team" "mate"}}))

(assert (= (__ ["veer" "lake" "item" "kale" "mite" "ever"])
   #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))
