import { render, screen } from '@testing-library/react';
import App from './App';

test('renders hostname', () => {
  render(<App />);
  const hostnameElement = screen.getByText(/Hostname/);
  expect(hostnameElement).toBeInTheDocument();
});
