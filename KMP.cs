public class Solution 
{
    // KMP search
    public int StrStr(string haystack, string needle) 
    {
        if (needle.Length == 0) return 0;
        
        int[] lps = new int[needle.Length];
        PreprocessLPS(needle, lps);
        
        int i=0,j=0;
        while (i < haystack.Length)
        {            
            if (haystack[i] == needle[j])
            {
                i++; j++;
                if (j == needle.Length) return i-needle.Length;
            }
            else if (j == 0) i++; 
            else j = lps[j-1];
        }
        return -1;
    }
    
    private void PreprocessLPS(string s, int[] lps)
    {
        int i=0,j=1;
        while (j < s.Length)
        {
            if (s[i] == s[j])
            {
                lps[j] = ++i;
                j++;
            }
            else if (i == 0) j++;
            else i = lps[i-1];
        }
    }
}