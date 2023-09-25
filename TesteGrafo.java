import java.util.*;

class GrafoMatriz {
    private int[][] matrizAdjacencia;
    private int numVertices;

    public GrafoMatriz(int numVertices) {
        this.numVertices = numVertices;
        matrizAdjacencia = new int[numVertices][numVertices];
    }

    public void adicionarAresta(int origem, int destino) {
        matrizAdjacencia[origem][destino] = 1;
        matrizAdjacencia[destino][origem] = 1;
    }

    public void imprimirBFS(int verticeInicio, int verticeFim) {
        Queue<Integer> fila = new LinkedList<>();
        boolean[] visitado = new boolean[numVertices];
        int[] pai = new int[numVertices];
        Arrays.fill(pai, -1);

        fila.add(verticeInicio);
        visitado[verticeInicio] = true;

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();

            for (int i = 0; i < numVertices; i++) {
                if (matrizAdjacencia[verticeAtual][i] == 1 && !visitado[i]) {
                    fila.add(i);
                    visitado[i] = true;
                    pai[i] = verticeAtual;
                }
            }
        }

        if (!visitado[verticeFim]) {
            System.out.println("Não há caminho entre os vértices.");
        } else {
            System.out.print("Caminho: ");
            imprimirCaminho(pai, verticeFim);
            System.out.println();
        }
    }

    public void imprimirDFS(int verticeInicio, int verticeFim) {
        boolean[] visitado = new boolean[numVertices];
        int[] pai = new int[numVertices];
        Arrays.fill(pai, -1);

        dfs(verticeInicio, verticeFim, visitado, pai);

        if (!visitado[verticeFim]) {
            System.out.println("Não há caminho entre os vértices.");
        } else {
            System.out.print("Caminho: ");
            imprimirCaminho(pai, verticeFim);
            System.out.println();
        }
    }

    private void dfs(int verticeAtual, int verticeFim, boolean[] visitado, int[] pai) {
        visitado[verticeAtual] = true;

        for (int i = 0; i < numVertices; i++) {
            if (matrizAdjacencia[verticeAtual][i] == 1 && !visitado[i]) {
                pai[i] = verticeAtual;
                if (i == verticeFim) {
                    return;
                }
                dfs(i, verticeFim, visitado, pai);
            }
        }
    }

    private void imprimirCaminho(int[] pai, int verticeAtual) {
        if (verticeAtual == -1) {
            return;
        }
        imprimirCaminho(pai, pai[verticeAtual]);
        System.out.print(verticeAtual + " ");
    }
}

class GrafoLista {
    private Map<Integer, List<Integer>> listaAdjacencia;
    private int numVertices;

    public GrafoLista(int numVertices) {
        this.numVertices = numVertices;
        listaAdjacencia = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            listaAdjacencia.put(i, new LinkedList<>());
        }
    }

    public void adicionarAresta(int origem, int destino) {
        listaAdjacencia.get(origem).add(destino);
        listaAdjacencia.get(destino).add(origem);
    }

    public void imprimirBFS(int verticeInicio, int verticeFim) {
        Queue<Integer> fila = new LinkedList<>();
        boolean[] visitado = new boolean[numVertices];
        int[] pai = new int[numVertices];
        Arrays.fill(pai, -1);

        fila.add(verticeInicio);
        visitado[verticeInicio] = true;

        while (!fila.isEmpty()) {
            int verticeAtual = fila.poll();

            for (int vizinho : listaAdjacencia.get(verticeAtual)) {
                if (!visitado[vizinho]) {
                    fila.add(vizinho);
                    visitado[vizinho] = true;
                    pai[vizinho] = verticeAtual;
                }
            }
        }

        if (!visitado[verticeFim]) {
            System.out.println("Não há caminho entre os vértices.");
        } else {
            System.out.print("Caminho: ");
            imprimirCaminho(pai, verticeFim);
            System.out.println();
        }
    }

    public void imprimirDFS(int verticeInicio, int verticeFim) {
        boolean[] visitado = new boolean[numVertices];
        int[] pai = new int[numVertices];
        Arrays.fill(pai, -1);

        dfs(verticeInicio, verticeFim, visitado, pai);

        if (!visitado[verticeFim]) {
            System.out.println("Não há caminho entre os vértices.");
        } else {
            System.out.print("Caminho: ");
            imprimirCaminho(pai, verticeFim);
            System.out.println();
        }
    }

    private void dfs(int verticeAtual, int verticeFim, boolean[] visitado, int[] pai) {
        visitado[verticeAtual] = true;

        for (int vizinho : listaAdjacencia.get(verticeAtual)) {
            if (!visitado[vizinho]) {
                pai[vizinho] = verticeAtual;
                if (vizinho == verticeFim) {
                    return;
                }
                dfs(vizinho, verticeFim, visitado, pai);
            }
        }
    }

    private void imprimirCaminho(int[] pai, int verticeAtual) {
        if (verticeAtual == -1) {
            return;
        }
        imprimirCaminho(pai, pai[verticeAtual]);
        System.out.print(verticeAtual + " ");
    }
}

public class TesteGrafo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Digite o número de vértices do grafo:");
            int numVertices = scanner.nextInt();

            GrafoMatriz grafoMatriz = new GrafoMatriz(numVertices);
            GrafoLista grafoLista = new GrafoLista(numVertices);

            System.out.println("Digite o número de arestas:");
            int numArestas = scanner.nextInt();
            
            System.out.println("Digite as arestas (origem destino):");
            for (int i = 0; i < numArestas; i++) {
                int origem = scanner.nextInt();
                int destino = scanner.nextInt();
                grafoMatriz.adicionarAresta(origem, destino);
                grafoLista.adicionarAresta(origem, destino);
            }

            System.out.println("Digite o vértice de partida:");
            int verticeInicio = scanner.nextInt();
            System.out.println("Digite o vértice de destino:");
            int verticeFim = scanner.nextInt();

            System.out.println("BFS com Matriz de Adjacência:");
            grafoMatriz.imprimirBFS(verticeInicio, verticeFim);

            System.out.println("BFS com Lista de Adjacência:");
            grafoLista.imprimirBFS(verticeInicio, verticeFim);

            System.out.println("DFS com Matriz de Adjacência:");
            grafoMatriz.imprimirDFS(verticeInicio, verticeFim);

            System.out.println("DFS com Lista de Adjacência:");
            grafoLista.imprimirDFS(verticeInicio, verticeFim);
        }
    }
}
