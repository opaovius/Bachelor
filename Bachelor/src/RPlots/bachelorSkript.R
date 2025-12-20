library(tidyverse)
library(ggplot2)
library(data.table)

df <- fread("~/eclipse-workspace/Uni/Bachelor/Bachelor/src/testFiles/AnalyseDir/Places/PlacesAnalyseTestEN.csv")

p <- ggplot(df,aes(x=Number)) +
  geom_histogram(bins = 50)

d <- ggplot_build(p)$data[[1]]

p <- ggplot(df, aes(x=Number)) +
  geom_histogram(bins = 50,
                 fill="#69b3a2",
                 color = "black") +
  labs(x = "Hashwert",
       y = "Anzahl Treffer",
       title = "TalAA") +
  annotate("text",
           x = Inf,
           y = Inf,
           label = paste("Maximum = ", max(d$count)),
           vjust = 1.5,
           hjust = 1.1) +
  theme_bw()

print(p)

bar <- ggplot(df,aes(x=Number)) +
  geom_bar()

f <- ggplot_build(bar)$data[[1]]

bar <- ggplot(df, aes(x=Number)) +
  geom_bar(aes(width = 10)) +
  labs(x = "Bitset Index",
       y = "Anzahl Mehrfachvorkommen",
       title = "TalAA") +
  annotate("text",
           x = Inf,
           y = Inf,
           label = paste("Maximum = ", max(f$count)),
           vjust = 1.5,
           hjust = 1.1) +
  theme_bw()

print(bar)



ggsave("test.png", bar, device = "png", path = "~/eclipse-workspace/Uni/Bachelor/Bachelor/src/testFiles/AnalyseDir/Places",
       width = 1920, height = 1080, units = "px")


ggsave("HistoMurmur.png", p, device = "png", path = "~/eclipse-workspace/Uni/Bachelor/Bachelor/src/RPlots",
       width = 1920, height = 1080, units = "px")
