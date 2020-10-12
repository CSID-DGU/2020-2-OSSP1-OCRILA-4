from gensim.models import Word2Vec

mylist = ["apple","applq","apply","applo","AI","Machine"]

vec = Word2Vec(mylist)

print(vec)
print("Hi")