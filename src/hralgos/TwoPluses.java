package hralgos;

/**
 * Created by predave on 6/7/17.
 *
 * for (int x = 1; x <= n; x++) {
 for (int y = 1;y <= m; y++){
 int r = 0;
 while (c[x + r][y] == 'G' && c[x - r][y] == 'G' &&
 c[x][y + r] == 'G' && c[x][y - r] == 'G') {
 c[x + r][y] = c[x - r][y] = c[x][y + r] = c[x][y - r] = 'g';
 for (int X = 1; X <= n; X++) {
 for (int Y = 1; Y <= m; Y++){
 int R = 0;
 while (c[X + R][Y] == 'G' && c[X - R][Y] == 'G' &&
 c[X][Y + R] == 'G' && c[X][Y - R] == 'G') {
 ans = max(ans, (1 + 4 * r) * (1 + 4 * R));
 R++;
 }
 }
 }
 r++;
 }
 r = 0;
 while (c[x + r][y] == 'g' && c[x - r][y] == 'g' &&
 c[x][y + r] == 'g' && c[x][y - r] == 'g') {
 c[x + r][y] = c[x - r][y] = c[x][y + r] = c[x][y - r] = 'G';
 r++;
 }
 }
 }
 */
public class TwoPluses {
}
