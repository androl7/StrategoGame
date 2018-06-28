# StrategoGame

Sztuczna inteligencja i Inżynieria wiedzy laboratorium
Ćwiczenie 3. Algorytmy rozwiązywania gier

1.	Wstęp
Grą użytą do realizacji ćwiczenia jest „Stratego” zwana inaczej „Pogrzebem”. Jest to gra dla 2 osób polegająca na tym że mając planszę N x N, gracze zamalowują po sobie kratki. Gdy zamalowane kratki wypełnią rząd, kolumnę lub linię ukośną na planszy, otrzymuje się tyle punktów, ile kratek liczy zakolorowany przez nas odcinek (minimum 2 kratki)


Zaimplementowane algorytmy:

•	Min-Max
Algorytm polega na tym, że gracz min chce minimalizować stan gry, a gracz max zmaksymalizować. Obliczamy drzewo wszystkich możliwych stanów w grze do zadanej głębokości. Po przeprowadzaniu tych symulacji wybierany zostaje ruch który jest najbardziej optymalny dla tej głębokości.

•	Alfa-Beta
Algorytm Alfa-Beta jest usprawnieniem algorytmu Min-Max, polega on na tym że odcinamy/nie sprawdzamy gałęzi, jeśli wiemy że wartości w nich są na pewno gorsze niż ta którą już wybraliśmy. Pozwala to na szybsze przeszukiwanie drzewa.

•	Alfa-Beta z sortowaniem
Usprawnieniem na które wpadłem jest sortowanie węzłów według ilości punktów. Powinno umożliwić to szybsze odcinanie węzłów które na pewno są gorsze od tego który już wybraliśmy. Dla max sortujemy od najwyższej wartości, dla min odwrotnie. Dzięki temu przeszukiwanie drzewa powinno się odbywać jeszcze sprawniej. 
