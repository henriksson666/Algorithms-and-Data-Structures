import networkx as nx
import matplotlib.pyplot as plt
import numpy as np

def receber_matriz_adjacencia():
    num_vertices = int(input("Digite o numero de vertices: "))
    print()

    matriz_adjacencia = [[0 for _ in range(num_vertices)] for _ in range(num_vertices)]

    for i in range(num_vertices):
        for j in range(num_vertices):
            valor = int(input(f"Digite o valor da aresta entre o vertice V{i+1} e o vertice V{j+1}: "))
            matriz_adjacencia[i][j] = valor

    return matriz_adjacencia

def e_grafo_simples(matriz_adjacencia):
    n = len(matriz_adjacencia)
    
    for i in range(n):
        for j in range(i + 1, n):
            if matriz_adjacencia[i][j] != matriz_adjacencia[j][i]:
                return False

    for i in range(n):
        if matriz_adjacencia[i][i] > 0:
            return False  

    for i in range(n):
        for j in range(n):
            if i != j and matriz_adjacencia[i][j] > 1:
                return False  

    return True  

def e_grafo_completo(matriz_adjacencia):
    n = len(matriz_adjacencia)

   
    for i in range(n):
        for j in range(i + 1, n):
            if matriz_adjacencia[i][j] != 1:
                return False  

    
    for i in range(n):
        if matriz_adjacencia[i][i] != 0:
            return False  

    return True  

def e_digrafo(matriz_adjacencia):
    n = len(matriz_adjacencia)

    for i in range(n):
        for j in range(n):
            if matriz_adjacencia[i][j] != matriz_adjacencia[j][i]:
                return True  

    return False  

def e_conexo(matriz_adjacencia):
    n = len(matriz_adjacencia)
    visitados = [False] * n

    def dfs(v):
        visitados[v] = True
        for u in range(n):
            if matriz_adjacencia[v][u] and not visitados[u]:
                dfs(u)

    vertice_inicial = None
    for i in range(n):
        if not visitados[i]:
            vertice_inicial = i
            break

    if vertice_inicial is None:
        return True

    dfs(vertice_inicial)

    return all(visitados)

def e_pseudografo(matriz_adjacencia):
    n = len(matriz_adjacencia)

    for i in range(n):
        for j in range(i + 1, n):
            if matriz_adjacencia[i][j] != matriz_adjacencia[j][i]:
                return False

    for i in range(n):
        if matriz_adjacencia[i][i] > 0:
            return True

    for i in range(n):
        for j in range(n):
            if i != j and matriz_adjacencia[i][j] > 1:
                return True

    return False

def possui_lacos(matriz_adjacencia):
    n = len(matriz_adjacencia)

    for i in range(n):
        if matriz_adjacencia[i][i] > 0:
            return True

    return False

def calcular_grau(matriz_adjacencia):
    n = len(matriz_adjacencia)
    
    graus_saida = [0] * n
    graus_entrada = [0] * n

    for i in range(n):
        for j in range(n):
            graus_saida[i] += matriz_adjacencia[i][j] 
            graus_entrada[j] += matriz_adjacencia[i][j]   

    return graus_saida, graus_entrada

def calcular_grau2(matriz_adjacencia):
    n = len(matriz_adjacencia)
    graus = [0] * n

    for i in range(n):
        for j in range(n):
            graus[i] += matriz_adjacencia[i][j]

    return graus

def matriz_adjacencia_para_digrafo(matriz_adjacencia):
    G = nx.DiGraph()  
    num_vertices = len(matriz_adjacencia)

    for i in range(num_vertices):
        for j in range(num_vertices):
            peso = matriz_adjacencia[i][j]
            if peso > 0:
                G.add_edge(i, j, weight=peso)

    return G

def matriz_adjacencia_para_grafo(matriz_adjacencia):
    G = nx.Graph()
    num_vertices = len(matriz_adjacencia)

    for i in range(num_vertices):
        for j in range(num_vertices):
            peso = matriz_adjacencia[i][j]
            if peso > 0:
                G.add_edge(i, j, weight=peso)

    return G

def verifica_numero_maior_que_um(matriz):
    for linha in matriz:
        for numero in linha:
            if numero > 1:
                return True
    return False

""" matriz_adjacencia = [
    [0, 1, 0],
    [1, 0, 2],
    [0, 0, 0]
]  """ 
matriz_adjacencia = receber_matriz_adjacencia()

for linha in matriz_adjacencia:
    for elemento in linha:
        print(elemento, end=' ')  
    print()  
    
if e_digrafo(matriz_adjacencia):
    if verifica_numero_maior_que_um(matriz_adjacencia):
        print("O grafo e um multigrafo dirigido.")
    else:
        print("O grafo e um digrafo.")
else:
    print("O grafo nao e um digrafo.")

if e_pseudografo(matriz_adjacencia):
    if possui_lacos(matriz_adjacencia):
        print("E um pseudografo.")
    else:
        print("E um multigrafo não dirigido.")
else:
    print("Nao e um pseudografo")
    
if e_grafo_simples(matriz_adjacencia):
    print("O grafo e simples.")
else:
    print("O grafo nao e simples.")
    
if e_grafo_completo(matriz_adjacencia):
    print("O grafo e completo.")
else:
    print("O grafo nao e completo.")
    
if possui_lacos(matriz_adjacencia):
    print("O grafo possui pelo menos um laco.")
else:
    print("O grafo nao possui lacos.")
    
if e_conexo(matriz_adjacencia):
    print("O grafo e conexo.")
else:
    print("O grafo nao e conexo.")
    
if e_digrafo(matriz_adjacencia):
    graus_saida, graus_entrada = calcular_grau(matriz_adjacencia)

    for i in range(len(graus_saida)):
        print(f"Vertice V{i+1}: Grau de saida = {graus_saida[i]}, Grau de entrada = {graus_entrada[i]}")
        
    G = matriz_adjacencia_para_digrafo(matriz_adjacencia)

    pos = nx.spring_layout(G)

    num_nodes = len(matriz_adjacencia)
    
    custom_labels = {node: f"V{node+1}" for node in G.nodes()}
    
    for node in range(num_nodes):
        if node not in G.nodes():
            G.add_node(node)
            pos[node] = (5, 0)  
    
    edge_labels = {}
    for i in range(num_nodes):
        for j in range(num_nodes):
            if matriz_adjacencia[i][j] != 0:
                edge_labels[(i, j)] = str(matriz_adjacencia[i][j])
    
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_color="red", verticalalignment="bottom", label_pos=0.8, rotate=False)


    nx.draw_networkx_labels(G, pos, labels=custom_labels, font_color="white")
    nx.draw_networkx_nodes(G, pos, node_size=300, node_color="blue")
    nx.draw_networkx_edges(G, pos, connectionstyle="arc3,rad=0.3", edge_color="gray")

    plt.show()
    
if e_pseudografo(matriz_adjacencia):
    graus = calcular_grau2(matriz_adjacencia)
    

    for i, grau in enumerate(graus):
        print(f"O vertice V{i+1} tem grau {grau}")
        
    matriz_de_arestas = matriz_adjacencia_para_grafo(matriz_adjacencia)
    num_nodes = len(matriz_adjacencia)

    G = nx.DiGraph(matriz_de_arestas)
    pos = nx.spring_layout(G)
    
    for node in range(num_nodes):
        if node not in G.nodes():
            G.add_node(node)
            pos[node] = (5, 0)  

    custom_labels = {node: f"V{node+1}" for node in G.nodes()}
    edge_labels = {(i, j): G[i][j]['weight'] for i, j in G.edges()}

    nx.draw_networkx_labels(G, pos, labels=custom_labels, font_color="white")
    nx.draw_networkx_nodes(G, pos, node_size=300, node_color="blue")
    nx.draw_networkx_edges(G, pos, connectionstyle="arc3,rad=0.3", edge_color="gray", arrows=False)
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_color="red")
    plt.show()

    
if e_grafo_simples(matriz_adjacencia):
    graus = calcular_grau2(matriz_adjacencia)

    for i, grau in enumerate(graus):
        print(f"O vertice V{i+1} tem grau {grau}")
        
    matriz_de_arestas = matriz_adjacencia_para_grafo(matriz_adjacencia)
    num_nodes = len(matriz_adjacencia)

    G = nx.DiGraph(matriz_de_arestas)
    pos = nx.spring_layout(G)

    
    edge_labels = {(i, j): G[i][j]['weight'] for i, j in G.edges()}
    
    for node in range(num_nodes):
        if node not in G.nodes():
            G.add_node(node)
            pos[node] = (5, 0)  
            
    custom_labels = {node: f"V{node+1}" for node in G.nodes()}


    nx.draw_networkx_labels(G, pos, labels=custom_labels, font_color="white")
    nx.draw_networkx_nodes(G, pos, node_size=300, node_color="blue")
    nx.draw_networkx_edges(G, pos, connectionstyle="arc3,rad=0.3", edge_color="gray", arrows=False)
    nx.draw_networkx_edge_labels(G, pos, edge_labels=edge_labels, font_color="red")
    plt.show()