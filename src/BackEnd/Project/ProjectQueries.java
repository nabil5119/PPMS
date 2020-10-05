package BackEnd.Project;

import BackEnd.Evaluate.EvaluateQueries;
import BackEnd.ProjectStatue.ProjectStatueQueries;
import BackEnd.Queries;
import BackEnd.ResToProject.ResToProjectQueries;
import BackEnd.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectQueries
{
    public static void addToDatabase(int id,String label,int idPor,int idType)
    {
        Queries.insertInto("projet",id+",'"+label+"',"+idPor+","+idType);
    }

    public static ResultSet getResultSet()
    {
        return Queries.getResultSet("projet");
    }

    public static Project getProjectById(int id)
    {
        ResultSet rs=Queries.getResultSetWhere("projet","*","id="+id);
        try
        {
            if(rs.next())
            {
                String label=rs.getString(2);
                int idPortfolio=rs.getInt(3);
                int idType=rs.getInt(4);
                return new Project(id,label,idPortfolio,idType);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int getPortfolioId(int idProject)
    {
        ResultSet rs=Queries.getResultSetWhere("projet","*","id="+idProject);
        try
        {
            if(rs.next())
            {
                return rs.getInt(3);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    public static void updateProject(int id,String label,int idPortfolio,int idType)
    {
        Queries.modifyCell("projet","libelle",label,"id="+id);
        Queries.modifyCell("projet","idTypeProjet",String.valueOf(idType),"id="+id);
        Queries.modifyCell("projet","idPortfeuille",String.valueOf(idPortfolio),"id="+id);
    }

    public static List<String> getProjectsRef()
    {
        List<String> projectsRef=new ArrayList<>();
        ResultSet rs=Queries.getResultSet("projet");
        try
        {
            while(rs.next())
            {
                projectsRef.add(rs.getString(2));
            }
        }
        catch (SQLException e)
        {e.printStackTrace();}

        return projectsRef;
    }

    public static void resetProject(int idProject,int idUser)
    {
        ResToProjectQueries.resetTable(idProject);
        EvaluateQueries.resetEvaluation(idProject,idUser);
        ProjectStatueQueries.addToDatabase(idProject,"Non Evalué",0, Utility.getDatetime());
    }

    public static void resetProject(int idProject)
    {
        ResToProjectQueries.resetTable(idProject);
        EvaluateQueries.resetEvaluation(idProject);
        ProjectStatueQueries.addToDatabase(idProject,"Non Evalué",0, Utility.getDatetime());
    }
}
