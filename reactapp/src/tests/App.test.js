import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';
import { QueryClient, QueryClientProvider } from 'react-query';
import store from '../store';
import Login from '../Components/Login';
import '@testing-library/jest-dom/extend-expect';
// import axios from 'axios';
import Signup from '../Components/Signup';
import ErrorPage from '../Components/ErrorPage';
import HomePage from '../Components/HomePage';
import MutualFundViewComponent from '../InvestorComponents/MutualFundViewComponent'
import CreateFund from '../FundManagerComponents/CreateFund'
import ViewInvestmentComponent from '../FundManagerComponents/ViewInvestmentComponent'
import ViewFundComponent from '../FundManagerComponents/ViewFundComponent'
import ViewFeedback from '../InvestorComponents/ViewFeedback'
import ViewTransaction from '../InvestorComponents/ViewTransaction'
import AdminViewTransactions from '../AdministratorComponents/AdminViewTransactions'
import AdministratorNavbar from '../AdministratorComponents/AdministratorNavbar'
import InvestorNavbar from '../InvestorComponents/InvestorNavbar'
import SubmitFeedback from '../InvestorComponents/SubmitFeedback'


jest.mock('axios');

// Setup QueryClient
const queryClient = new QueryClient();

describe('Login Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderLoginComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Login {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_login_component_renders_the_with_login_heading', () => {
    renderLoginComponent();

  
    const loginHeadings = screen.getAllByText(/Login/i);
    expect(loginHeadings.length).toBeGreaterThan(0);

  });


  test('frontend_login_component_displays_validation_messages_when_login_button_is_clicked_with_empty_fields', () => {
    renderLoginComponent();

    fireEvent.click(screen.getByRole('button', { name: /Login/i }));

    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
  });

   
});
describe('Signup Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderSignupComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <Signup {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_signup_component_renders_with_signup_heading', () => {
    renderSignupComponent();

    const signupHeadings = screen.getAllByText(/Signup/i);
   expect(signupHeadings.length).toBeGreaterThan(0);

  });

  test('frontend_signup_component_displays_validation_messages_when_submit_button_is_clicked_with_empty_fields', () => {
    renderSignupComponent();

    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(screen.getByText('User Name is required')).toBeInTheDocument();
    expect(screen.getByText('Email is required')).toBeInTheDocument();
    expect(screen.getByText('Mobile Number is required')).toBeInTheDocument();
    expect(screen.getByText('Password is required')).toBeInTheDocument();
    expect(screen.getByText('Confirm Password is required')).toBeInTheDocument();
  });

  test('frontend_signup_component_displays_error_when_passwords_do_not_match', () => {
    renderSignupComponent();

    fireEvent.change(screen.getByPlaceholderText('Password'), { target: { value: 'password123' } });
    fireEvent.change(screen.getByPlaceholderText('Confirm Password'), { target: { value: 'password456' } });
    fireEvent.click(screen.getByRole('button', { name: /Submit/i }));

    expect(screen.getByText('Passwords do not match')).toBeInTheDocument();
  });
});

describe('ErrorPage Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderErrorComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ErrorPage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_errorpage_component_renders_with_error_heading', () => {
    renderErrorComponent();
    const headingElement = screen.getByText(/Oops! Something Went Wrong/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_errorpage_component_renders_with_error_content', () => {
    renderErrorComponent();
    const paragraphElement = screen.getByText(/Please try again later./i);
    expect(paragraphElement).toBeInTheDocument();
  });
});
describe('Home Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });
  const renderHomeComponent = (props = {}) => {
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <HomePage {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  
  test('frontend_home_component_renders_with_heading', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/MutualFundMS/i);
    expect(headingElement.length).toBeGreaterThan(0);

  });
  test('frontend_home_component_renders_with_contact_us', () => {
    renderHomeComponent();
    const headingElement = screen.getAllByText(/Contact Us/i);
    expect(headingElement.length).toBeGreaterThan(0);

  });
});

describe('MutualFundViewComponent Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderMutualFundViewComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <MutualFundViewComponent {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_MutualFundViewComponent_Investor_component_renders_the_with_heading', () => {
    renderMutualFundViewComponent();

    const headingElement = screen.getByText(/Available Mutual Funds/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_MutualFundViewComponent_Investor_component_renders_the_button', () => {
    renderMutualFundViewComponent();

    const tableElement = screen.getByRole('button');
    expect(tableElement).toBeInTheDocument();
  });
});

describe('CreateFund Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });


  const renderCreateFund = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <CreateFund {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_CreateFund_fundmanager_component_renders_the_with_heading', () => {
    renderCreateFund();

    const headingElement = screen.getByText(/Create New Mutual Fund/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_CreateFund_fundmanager_component_displays_required_validation_messages', async () => {
    renderCreateFund();

    fireEvent.click(screen.getByRole('button', { name: /Create Mutual Fund/i }));

    expect(await screen.findByText('Fund Name is required')).toBeInTheDocument();
    expect(await screen.findByText('Current NAV must be a positive number')).toBeInTheDocument();
    expect(await screen.findByText('Expense Ratio must be between 0 and 100')).toBeInTheDocument();
    expect(await screen.findByText('Prospectus is required')).toBeInTheDocument();
  });
});

describe('ViewInvestmentComponent Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

const renderViewInvestmentComponent = (props = {}) => {
  const queryClient = new QueryClient();
  return render(
    <Provider store={store}>
    <QueryClientProvider client={queryClient}>
      <Router>
        <ViewInvestmentComponent {...props} />
      </Router>
    </QueryClientProvider>
    </Provider>
  );
};
// axios.get.mockResolvedValue();


test('frontend_ViewInvestmentComponent_FundManager_component_renders_the_heading', async () => {
  // axios.get.mockResolvedValue({
  //   data: [
  //     {
  //       InvestmentId: 1,
  //       Investor: { Username: 'John Doe' },
  //       Fund: { FundName: 'Tech Fund' },
  //       AmountInvested: 1000,
  //       UnitsPurchased: 10,
  //       CurrentValue: 1100,
  //       PurchaseDate: '2023-09-01T00:00:00Z',
  //     },
  //   ],
  // });
  renderViewInvestmentComponent();

  const headingElement = await screen.getByText(/All Investments/i);
  expect(headingElement).toBeInTheDocument();
});

test('frontend_ViewInvestmentComponent_FundManager_component_displays_No_investments_available', () => {
  renderViewInvestmentComponent();

  const loadingElement = screen.getByText(/No investments available/i);
  expect(loadingElement).toBeInTheDocument();
});
});

describe('ViewFundComponent Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewFundComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewFundComponent {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ViewFundComponent_component_renders_the_heading', () => {
    // Mock the API response
    // axios.get.mockResolvedValue();
    
    renderViewFundComponent();

    const headingElement = screen.getByText(/Available Mutual Funds/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_ViewFundComponent_displays_no_funds_message_when_no_data_is_present', () => {
    // Mock the API response with an empty array
    // axios.get.mockResolvedValue({ data: [] });
    
    renderViewFundComponent();

    const noFundsElement = screen.getByText(/No mutual funds available/i);
    expect(noFundsElement).toBeInTheDocument();
  });
})

describe('ViewFeedback Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewFeedback = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewFeedback {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };

  test('frontend_ViewFeedback_component_renders_the_heading', () => {
    // Mock the API response
    // axios.get.mockResolvedValue();
    
    renderViewFeedback();

    const headingElement = screen.getByText(/Your Feedback/i);
    expect(headingElement).toBeInTheDocument();
  });

  test('frontend_ViewFeedback_displays_no_funds_message_when_no_data_is_present', async () => {
    // Mock the API response with an empty array
    // axios.get.mockResolvedValue({ data: [] });
    
    renderViewFeedback();

    const noFeedbackMessage = await screen.findByText(/No feedback available/i);
    expect(noFeedbackMessage).toBeInTheDocument();
  });
});

describe('TransactionHistory Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderViewTransactionHistoryComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <ViewTransaction {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_ViewTransaction_investor_component_renders_the_with_heading', () => {
    renderViewTransactionHistoryComponent();
    const headingElement = screen.getAllByText(/Transaction History/i);
    expect(headingElement.length).toBeGreaterThan(0);
  });

  test('frontend_ViewTransaction_investor_component_renders_the_table', () => {
    renderViewTransactionHistoryComponent();
    
    const tableElement = screen.getByRole('table');
    expect(tableElement).toBeInTheDocument();
  });

});

describe('AdministratorNavbar Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderAdministratorNavbarComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <AdministratorNavbar {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_AdministratorNavbar_Admin_component_renders_the_with_Transaction_menu', async () => {
    // axios.get.mockResolvedValue({
    // });
    renderAdministratorNavbarComponent();
    // const headingElement =await screen.findByText(/Transaction List/i);
    // expect(headingElement.length).toBeGreaterThan(0);
    const headingElement = screen.getAllByText(/Transaction/i);
    expect(headingElement.length).toBeGreaterThan(0);
  });

});

describe('InvestorNavbar Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderInvestorNavbarComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <InvestorNavbar {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_InvestorNavbar_Investor_component_renders_the_with_Funds_menu', async () => {
    // axios.get.mockResolvedValue({
    // });
    renderInvestorNavbarComponent();
    const headingElement = screen.getAllByText(/Funds/i);
    expect(headingElement.length).toBeGreaterThan(0);
  });

});

describe('SubmitFeedback Component', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  const renderSubmitFeedbackComponent = (props = {}) => {
    const queryClient = new QueryClient(); // Create a new QueryClient instance
    return render(
      <Provider store={store}>
        <QueryClientProvider client={queryClient}>
          <Router>
            <SubmitFeedback {...props} />
          </Router>
        </QueryClientProvider>
      </Provider>
    );
  };
  test('frontend_SubmitFeedback_Investor_component_renders_the_heading', async () => {
    // axios.get.mockResolvedValue({
    // });
    renderSubmitFeedbackComponent();
    const headingElement = screen.getAllByText(/Submit Feedback/i);
    expect(headingElement.length).toBeGreaterThan(0);
  });

  test('frontend_SubmitFeedback_Investor_component_renders_the_forms_Submit_Button', async () => {
    // axios.get.mockResolvedValue({
    // });
    renderSubmitFeedbackComponent();
    const submitButton = screen.getByRole('button',  { name: /Submit Feedback/i });
    expect(submitButton).toBeInTheDocument();
  });

});