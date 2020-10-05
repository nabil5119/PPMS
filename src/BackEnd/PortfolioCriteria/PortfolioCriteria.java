package BackEnd.PortfolioCriteria;

public class PortfolioCriteria
{
    private int id;
    private int idPortfolio;
    private int weight;
    private String genre;

    public PortfolioCriteria(int id,int idPortfolio,int weight,String genre)
    {
        this.id=id;
        this.idPortfolio=idPortfolio;
        this.weight=weight;
        this.genre=genre;
    }

    public int getId()
    {
        return id;
    }
    public int getWeight()
    {
        return weight;
    }

    public String getGenre()
    {
        return genre;
    }
    
    public int getIdPortfolio()
    {
    	return idPortfolio;
    }
}
