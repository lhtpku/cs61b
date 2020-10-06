/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int res = 0;
        int layer = 0;
        int tmp = N;
        while (tmp != 1) {
            layer++;
            tmp /= 2;
        }
        for (int i = 0; i < layer; i++) {
            res += (int) Math.pow(2, i) * i;
        }
        res += (N - (int) Math.pow(2, layer) + 1) * layer;
        return res;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N)/N;
    }

    public static void getInt(BST<Integer> T){
        int temp = RandomGenerator.getRandomInt(10000);
        while (true){
            if(!T.contains(temp)){
                T.add(temp);
                break;
            }else{
                temp = RandomGenerator.getRandomInt(10000);
            }
        }
    }
}
