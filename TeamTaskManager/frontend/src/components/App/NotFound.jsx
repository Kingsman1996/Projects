export default function NotFound() {
    return (
        <div className="flex flex-col items-center justify-center h-screen">
            <h1 className="text-2xl font-bold mb-4">404 Not Found</h1>
            <p className="text-lg">The page you are looking for does not exist.</p>
            <p className="text-sm text-gray-500 mt-2">Please check the URL or return to the home page.</p>
        </div>
    )
}