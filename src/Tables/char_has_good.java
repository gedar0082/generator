package Tables;

public class char_has_good extends Table{
    int[][] indices;
    int size;

    public int[] getRandomConnection(){
        int[] con = {2};
        con[0] = rnd(0, 1);
        return con;
    }

    private boolean validation(int[] id){
        if (indices.length == 0) return false;
        for (int i = 0; i < size; i++){
            if (indices[i][0]==id[0] && indices[i][1]==id[1]) return false;
        }
        return true;
    }
}
