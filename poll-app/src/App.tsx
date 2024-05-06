import Footer from './components/Footer';
import PollView from './components/PollView';
import './styles/App.css';
import './styles/style.css'


function App() {
  return (
    <div className="App">
      <header className="App-header">
        <PollView />
        <Footer />
      </header>
    </div>
  );
}

export default App;
