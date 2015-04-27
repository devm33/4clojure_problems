(ns problems.49)

; split-at n xs

; my solution

(fn [n xs] [(take n xs) (drop n xs)])

; nifty solution

(juxt take drop)

; juxt takes a group of functions and returns a function that
; applies its args to each function