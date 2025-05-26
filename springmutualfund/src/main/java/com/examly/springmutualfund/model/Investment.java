

@Entity
public class Investment {

    private Long investmentId;

    // Linked to Investor -> User
    private Long investorId;
    
    // Linked to MutualFund
    private int mutualFundId;
    
}
